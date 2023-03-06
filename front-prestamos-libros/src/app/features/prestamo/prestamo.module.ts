import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule } from '@angular/forms';

import { PrestamoRoutingModule } from './prestamo-routing.module';

import { CrearPrestamoComponent } from './pages/crear-prestamo/crear-prestamo.component';
import { ListarPrestamosComponent } from './pages/listar-prestamos/listar-prestamos.component';
import { DetallePrestamoComponent } from './pages/detalle-prestamo/detalle-prestamo.component';

import { PrestamoService } from './services/prestamo.service';

/* Shared */
import { AngularMaterialModule } from 'src/app/shared/angular-material.module';
import { DialogModule } from 'src/app/shared/dialog/dialog.module';
import { ConsultarPrestamosComponent } from './components/consultar-prestamos/consultar-prestamos.component';
import { ListarActivosComponent } from './pages/listar-activos/listar-activos.component';

@NgModule({
  declarations: [CrearPrestamoComponent, ListarPrestamosComponent, DetallePrestamoComponent, ConsultarPrestamosComponent, ListarActivosComponent],
  imports: [CommonModule, PrestamoRoutingModule, AngularMaterialModule, ReactiveFormsModule, DialogModule],
  providers: [PrestamoService],
})
export class PrestamoModule {}
