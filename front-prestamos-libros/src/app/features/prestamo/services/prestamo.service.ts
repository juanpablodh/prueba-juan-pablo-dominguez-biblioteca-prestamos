import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';

/* Interfaces y Modelos*/
import { IPrestamoFull, ICrearPrestamo, IPrestamoSimple } from '../interfaces/prestamo.interface';

@Injectable({
  providedIn: 'root',
})
export class PrestamoService {
  constructor(private http: HttpClient) {}

  obtenerTodos(): Observable<IPrestamoFull[]> {
    return this.http.get<IPrestamoFull[]>(`${environment.API_URL}prestamo`);
  }

  obtenerTodosActivos(): Observable<IPrestamoFull[]> {
    return this.http.get<IPrestamoFull[]>(`${environment.API_URL}prestamo/activos`);
  }

  obtenerPorId(id: number): Observable<IPrestamoFull> {
    return this.http.get<IPrestamoFull>(`${environment.API_URL}prestamo/${id}`);
  }

  crear(prestamo: ICrearPrestamo): Observable<IPrestamoSimple> {
    return this.http.post<IPrestamoSimple>(`${environment.API_URL}prestamo`, prestamo);
  }

  eliminar(id: number): Observable<IPrestamoFull> {
    return this.http.delete<IPrestamoFull>(`${environment.API_URL}prestamo/${id}`);
  }
}
