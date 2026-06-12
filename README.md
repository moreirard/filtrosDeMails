# Filtros de Correo Electrónico

## Objetivo

Se desea modelar un sistema de filtrado de correos electrónicos similar al que poseen los clientes de correo modernos.

Cada correo puede evaluarse mediante distintos criterios para determinar si cumple determinadas condiciones. Además, debe ser posible combinar varios filtros para construir reglas más complejas.

---

## Dominio

Un correo electrónico posee la siguiente información:

- Remitente (`from`)
- Destinatario (`to`)
- Asunto
- Tamaño en KB
- Indicación de si posee o no archivos adjuntos
- Cuerpo del correo

El sistema debe permitir definir filtros sobre los correos.

Algunos ejemplos de filtros son:

- El correo tiene archivos adjuntos.
- El asunto contiene una determinada palabra.
- El tamaño del correo supera un valor mínimo.

---

## Requerimientos

### Filtros simples

Implementar filtros que permitan verificar:

- Si el correo posee adjuntos.
- Si el asunto contiene un texto determinado.
- Si el tamaño del correo es mayor o igual a un valor indicado.

Cada filtro debe poder aplicarse de manera independiente sobre un correo.

---

### Filtros compuestos

El sistema debe permitir construir filtros a partir de otros filtros.

Por ejemplo:

- "Tiene adjunto y el tamaño es mayor a 200 KB".
- "Tiene adjunto y el asunto contiene la palabra *importante*".
- "Tiene adjunto, el tamaño es mayor a 200 KB y el asunto contiene la palabra *factura*".

Un filtro compuesto debe considerar que un correo cumple la condición únicamente cuando **todos** los filtros que lo integran resultan verdaderos.

---

### Configuración de filtros

Cada cuenta de correo puede tener asociado un filtro activo.

Al solicitar la lista de correos electrónicos, se deberá aplicar el filtro configurado.

---

## Ejemplo de uso

Dado el correo:

- Asunto: `"Muy importante"`
- Tamaño: `250 KB`
- Posee adjunto: `Sí`
- Cuerpo: `Reunión de consorcio para tratar problema con ascensor 1`

Y el filtro compuesto:

- Tiene adjunto.
- Tamaño mayor o igual a `230 KB`.
- El asunto contiene `"importante"`.

El resultado de la lista de correos debe obtener el correo de ejemplo, ya que el asunto contiene el texto requerido.

---

## Desafío adicional

Extender el modelo incorporando:

- Filtros **OR**.
- Filtros **NOT**.
- Filtros por remitente.
- Filtros por destinatario.
- Filtros anidados (compuestos dentro de compuestos).

El diseño debe permitir agregar nuevos filtros sin modificar los ya existentes.
Plantear nuevos casos de prueba que verifiquen el funcionamiento correcto de al menos 1 desafío adicional
