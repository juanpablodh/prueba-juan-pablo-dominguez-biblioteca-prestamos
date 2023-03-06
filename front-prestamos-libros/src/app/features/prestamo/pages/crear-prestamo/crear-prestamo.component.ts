import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PrestamoService } from '../../services/prestamo.service';

import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

import { MatSnackBar } from '@angular/material/snack-bar';

import { ICrearPrestamo, ITipoUsuario } from '../../interfaces/prestamo.interface';
@Component({
  selector: 'app-crear-prestamo',
  templateUrl: './crear-prestamo.component.html',
  styleUrls: ['./crear-prestamo.component.css'],
})
export class CrearPrestamoComponent implements OnInit {
  formulario: FormGroup;

  tiposUsuario: ITipoUsuario[] = [
    { id: 1, descripcion: 'Afiliado' },
    { id: 2, descripcion: 'Empleado' },
    { id: 3, descripcion: 'Invitado' },
  ];

  constructor(
    private _route: ActivatedRoute,
    private _prestamoService: PrestamoService,
    private _formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private _router: Router
  ) {
    this.formulario = this.construirFormulario();
  }

  ngOnInit(): void {}

  construirFormulario(): FormGroup {
    return this._formBuilder.group({
      isbn: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      identificacionUsuario: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      tipoUsuario: new FormControl('', [Validators.required]),
    });
  }

  prestar(evento: Event) {
    evento.preventDefault();
    if (this.formulario.valid) {
      const prestamo: ICrearPrestamo = this.formulario.value;

      this._prestamoService.crear(prestamo).subscribe((respuesta) => {
        const snackBarRef = this._snackBar.open('Prestamo realizado exitosamente', 'Cerrar', {
          duration: 5000,
          panelClass: 'snackBarExitoso',
        });

        snackBarRef.afterDismissed().subscribe(() => {
          this._router.navigate(['prestamo/listar']);
        });
      });
    } else {
      this._snackBar.open('Antes de continuar, revisa los errores marcados en el formulario', 'Cerrar', {
        duration: 5000,
      });
      this.formulario.markAllAsTouched();
    }
  }
}
