import { HttpErrorResponse, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';

import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({ providedIn: 'root' })
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor(private snackBar: MatSnackBar) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error) => {
        let mensajeError = '';
        if (error instanceof HttpErrorResponse) {
          if (error.error.mensaje) {
            mensajeError = error.error.mensaje;
            this.snackBar.open(mensajeError, 'cerrar', { duration: 10000 });
          } else {
            mensajeError = 'Revisa los siguientes campos: \n';
            Object.keys(error.error).forEach((propiedadCampoError) => {
              mensajeError += `* El campo [${propiedadCampoError}]: ${error.error[propiedadCampoError]} \n`;
            });
            this.snackBar.open(mensajeError, 'cerrar', { duration: 10000, panelClass: 'error-mensaje-campos-snackbar' });
          }
        }

        return throwError(error);
      })
    );
  }
}

export const proveedorErrorInterceptor = [{ provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }];
