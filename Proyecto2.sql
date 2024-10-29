CREATE DATABASE Seguros;

USE Seguros;

create table Usuario (
  id varchar(10) not null,
  clave varchar(10),
  tipo integer,
  Primary key(id)
);

create table Cliente (
  cedula varchar(10) not null,
  clave varchar(10) not null,
  nombre varchar(30),
  telefono varchar(15),
  correo varchar(30),
  tarjeta varchar(20),
  usuario varchar(10),
  Primary key(cedula)
);

alter table Cliente add foreign key (usuario) references Usuario(id);

create table Marca (
  id integer auto_increment not null,
  descripcion varchar(15),
  Primary key(id)
);

create table Modelo (
  id integer auto_increment not null,
  descripcion varchar(15),
  marca integer,
  Primary key(id)
);

alter table Modelo add foreign key (marca) references Marca(id);

create table Poliza (
  id integer auto_increment not null,
  num_Placa varchar(10) not null,
  marca integer,
  modelo integer,
  anio integer,
  valor_asegurado integer,
  plazos_pago varchar(15),
  fecha varchar(15),
  cliente varchar(10),
  Primary key(id)
);

alter table Poliza add foreign key (cliente) references Cliente(cedula);
alter table Poliza add foreign key (marca) references Marca(id);
alter table Poliza add foreign key (modelo) references Modelo(id);
alter table Poliza auto_increment=3;

create table Categoria (
  id integer auto_increment not null,
  descripcion varchar(30),
  Primary key(id)
);

create table Cobertura (
  id integer auto_increment not null,
  descripcion varchar(30),
  costo_minimo integer,
  costo_porcentual integer,
  categoria integer,
  Primary key(id)
);

alter table Cobertura add foreign key (categoria) references Categoria(id);

create table PolizaCobertura (
	poliza integer,
	cobertura integer
);

alter table PolizaCobertura add foreign key (poliza) references Poliza(id);
alter table PolizaCobertura add foreign key (cobertura) references Cobertura(id);


insert into Usuario (id,clave,tipo) 
	values ("111","111",1);
insert into Usuario (id,clave,tipo) 
	values ("222","222",1);
insert into Usuario (id,clave,tipo) 
	values ("333","333",2);	

insert into Cliente (cedula,clave,nombre,telefono,correo,tarjeta,usuario) 
	values ("111","111","Ibai","8787-5968","111@gmail.com","5454","111");	
insert into Cliente (cedula,clave,nombre,telefono,correo,tarjeta,usuario) 
	values ("222","222","Juan","2222-2222","222@gmail.com","2222","222");
	
insert into Marca (id,descripcion)
	values (1,"Nissan");
insert into Marca (id,descripcion)
	values (2,"Toyota");
	
insert into Modelo (id,descripcion,marca)
	values (1,"Nissan-Sentra",1);
insert into Modelo (id,descripcion,marca)
	values (2,"Toyota-Corolla",2);

insert into Poliza (id,num_Placa,marca,modelo,anio,valor_asegurado,plazos_pago,fecha,cliente) 
	values (1,"4545",1,1,2017,5000,"hhj","7/4/2023","111");	
insert into Poliza (id,num_Placa,marca,modelo,anio,valor_asegurado,plazos_pago,fecha,cliente) 
	values (2,"5858",2,2,2017,5000,"hhj","8/4/2023","111");

insert into Categoria (id,descripcion)
	values (1,"Responsabilidad civil");
insert into Categoria (id,descripcion)
	values (2,"Danio directo");
	
insert into Cobertura (id,descripcion,costo_minimo,costo_porcentual,categoria)
	values (1,"Danio a personas",500,25,1);
insert into Cobertura (id,descripcion,costo_minimo,costo_porcentual,categoria)
	values (2,"Danio a bienes",400,20,1);
insert into Cobertura (id,descripcion,costo_minimo,costo_porcentual,categoria)
	values (3,"Gastos legales",300,15,1);
insert into Cobertura (id,descripcion,costo_minimo,costo_porcentual,categoria)
	values (4,"Danio al auto",250,10,2);
insert into Cobertura (id,descripcion,costo_minimo,costo_porcentual,categoria)
	values (5,"Robo",200,5,2);
	
insert into PolizaCobertura (poliza,cobertura)
	values (1,4);
insert into PolizaCobertura (poliza,cobertura)
	values (1,2);
insert into PolizaCobertura (poliza,cobertura)
	values (2,2);