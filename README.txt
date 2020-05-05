Ejemplos de uso:
- Leer el archivo a través de la entrada estándar de datos, mostrando los bytes en grupos de 4 junto con su dirección a la izquierda seguida de ":" y mostrando a la derecha una interpretación ASCII de los bytes en el rango imprimible (\x00, \x10, etc se muestran como ".", como es costumbre con los programas estilo hexdump):
  [programa] -s 4 -c -x -a
- Comando equivalente, pero utilizando los nombres largos de parámetros equivalentes:
  [programa] --chunk-size 4 --colon --show-offset --show-ascii
- Leer /tmp/file.bin y mostrar cada byte separado del resto, con cada grupo de 16 bytes en una línea con la dirección del primero antepuesta sin ":":
  [programa] --show-offset -f /tmp/file.bin
