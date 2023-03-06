import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PrestamoService } from '../../services/prestamo.service';

import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-detalle-prestamo',
  templateUrl: './detalle-prestamo.component.html',
  styleUrls: ['./detalle-prestamo.component.css'],
})
export class DetallePrestamoComponent implements OnInit {
  formulario: FormGroup;

  constructor(private _route: ActivatedRoute, private _prestamoService: PrestamoService, private _formBuilder: FormBuilder, private _router: Router) {
    this.formulario = this.construirFormulario();
  }

  ngOnInit(): void {
    const idPrestamo: number = this._route.snapshot.params['id'];

    this._prestamoService.obtenerPorId(idPrestamo).subscribe(
      (prestamo) => {
        if (prestamo) {
          this.formulario.setValue({
            id: prestamo.id,
            isbn: prestamo.isbn,
            identificacionUsuario: prestamo.identificacionUsuario,
            tipoUsuario: prestamo.tipoUsuario == 1 ? 'Afiliado' : prestamo.tipoUsuario == 2 ? 'Empleado' : 'Invitado',
            fechaMaximaDevolucion: prestamo.fechaMaximaDevolucion,
          });
        }
      },
      (error) => {
        this._router.navigate(['prestamo/listar']);
      }
    );
  }

  construirFormulario(): FormGroup {
    return this._formBuilder.group({
      id: new FormControl(),
      isbn: new FormControl(),
      identificacionUsuario: new FormControl(),
      tipoUsuario: new FormControl(),
      fechaMaximaDevolucion: new FormControl(),
    });
  }
}
