export interface IPrestamoFull {
  id: number;
  isbn: string;
  identificacionUsuario: string;
  tipoUsuario: number;
  fechaMaximaDevolucion: Date;
}

export interface IPrestamoSimple {
  isbn: string;
  identificacionUsuario: string;
}

export interface ICrearPrestamo {
  isbn: string;
  identificacionUsuario: string;
  tipoUsuario: number;
}

export interface ITipoUsuario {
  id: number;
  descripcion: string;
}
