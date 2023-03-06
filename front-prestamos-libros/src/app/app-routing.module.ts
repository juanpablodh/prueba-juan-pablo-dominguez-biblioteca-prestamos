import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'prestamo', loadChildren: () => import('./features/prestamo/prestamo.module').then(m => m.PrestamoModule) },
  {
    path: '**',
    redirectTo: 'prestamo',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
