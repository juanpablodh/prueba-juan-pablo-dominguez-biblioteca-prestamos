import { Component, OnInit } from '@angular/core';

import { IPrestamoFull } from '../../interfaces/prestamo.interface';
import { PrestamoService } from '../../services/prestamo.service';

@Component({
  selector: 'app-listar-activos',
  templateUrl: './listar-activos.component.html',
  styleUrls: ['./listar-activos.component.css'],
})
export class ListarActivosComponent implements OnInit {
  prestamos: IPrestamoFull[] = [];

  constructor(private _prestamoService: PrestamoService) {}

  ngOnInit(): void {
    this.consultarPrestamos();
  }

  consultarPrestamos() {
    this._prestamoService.obtenerTodosActivos().subscribe((respuesta) => {
      this.prestamos = respuesta;
    });
  }
}
