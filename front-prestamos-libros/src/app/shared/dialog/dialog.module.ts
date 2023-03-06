import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogConfirmationComponent } from './components/dialog-confirmation/dialog-confirmation.component';

@NgModule({
  declarations: [DialogConfirmationComponent],
  imports: [CommonModule],
  providers: [],
  exports: [DialogConfirmationComponent],
})
export class DialogModule {}
