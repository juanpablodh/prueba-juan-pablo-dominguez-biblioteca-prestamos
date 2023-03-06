import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { MatDialog } from '@angular/material/dialog';
import { DialogConfirmationComponent } from 'src/app/shared/dialog/components/dialog-confirmation/dialog-confirmation.component';

import { IPrestamoFull } from '../../interfaces/prestamo.interface';

import { PrestamoService } from '../../services/prestamo.service';

import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-consultar-prestamos',
  templateUrl: './consultar-prestamos.component.html',
  styleUrls: ['./consultar-prestamos.component.css'],
})
export class ConsultarPrestamosComponent implements OnInit {
  @Input()
  prestamos: IPrestamoFull[] = [];

  @Input()
  callbackConsultar!: () => void;

  displayedColumns: string[] = ['id', 'isbn', 'identificacionUsuario', 'tipoUsuario', 'fechaMaximaDevolucion', 'acciones'];

  constructor(private _prestamoService: PrestamoService, private _router: Router, public dialogo: MatDialog, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {}

  verDetalle(id: number) {
    this._router.navigate(['prestamo/detalle', id]);
  }

  eliminar(id: number) {
    this.mostrarDialogo(id);
  }

  mostrarDialogo(id: number): void {
    this.dialogo
      .open(DialogConfirmationComponent, {
        data: `¿Estas seguro que deseas eliminar el prestamo?`,
      })
      .afterClosed()
      .subscribe((confirmado: Boolean) => {
        if (confirmado) {
          this._prestamoService.eliminar(id).subscribe((prestamoEliminado) => {
            this._snackBar.open('Se ha eliminado el Prestamo exitosamente', 'Cerrar', {
              duration: 5000,
            });
            this.callbackConsultar();
          });
        }
      });
  }
}
