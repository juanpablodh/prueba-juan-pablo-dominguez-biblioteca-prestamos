import { Component, OnInit } from '@angular/core';

import { IPrestamoFull } from '../../interfaces/prestamo.interface';
import { PrestamoService } from '../../services/prestamo.service';

@Component({
  selector: 'app-listar-prestamos',
  templateUrl: './listar-prestamos.component.html',
  styleUrls: ['./listar-prestamos.component.css'],
})
export class ListarPrestamosComponent implements OnInit {
  prestamos: IPrestamoFull[] = [];

  constructor(private _prestamoService: PrestamoService) {}

  ngOnInit(): void {
    this.consultarPrestamos();
  }

  consultarPrestamos() {
    this._prestamoService.obtenerTodos().subscribe((respuesta) => {
      this.prestamos = respuesta;
    });
  }
}
