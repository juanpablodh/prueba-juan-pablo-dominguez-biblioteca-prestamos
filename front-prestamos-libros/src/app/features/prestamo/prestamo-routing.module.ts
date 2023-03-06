import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CrearPrestamoComponent } from './pages/crear-prestamo/crear-prestamo.component';
import { DetallePrestamoComponent } from './pages/detalle-prestamo/detalle-prestamo.component';
import { ListarActivosComponent } from './pages/listar-activos/listar-activos.component';
import { ListarPrestamosComponent } from './pages/listar-prestamos/listar-prestamos.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'listar',
  },
  {
    path: 'listar',
    component: ListarPrestamosComponent,
  },
  {
    path: 'crear',
    component: CrearPrestamoComponent,
  },
  {
    path: 'detalle/:id',
    component: DetallePrestamoComponent,
  },
  {
    path: 'activos',
    component: ListarActivosComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PrestamoRoutingModule {}
