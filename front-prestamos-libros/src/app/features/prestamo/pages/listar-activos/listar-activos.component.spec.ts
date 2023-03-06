import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarActivosComponent } from './listar-activos.component';

describe('ListarActivosComponent', () => {
  let component: ListarActivosComponent;
  let fixture: ComponentFixture<ListarActivosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarActivosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarActivosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
