#per eseguire container docker: docker container run -it marmann/progettoisa:2022 bash 
FROM maven:3.8.5-jdk-11 
WORKDIR home/ProgettoISA/mioProgetto
# copio il file pom.xml nella WORKDIR 
# COPY <src> <dest> 
COPY pom.xml . 
# copio la cartella src nella WORKDIR 
# importante specificare src/ così crea la 
# cartella e non copia solamente il contenuto 
COPY src src/