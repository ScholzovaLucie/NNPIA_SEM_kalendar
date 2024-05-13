# Kalendářová Aplikace

Tato kalendářová aplikace je moderní řešení pro správu osobních a pracovních kalendářů. Aplikace umožňuje uživatelům spravovat úkoly, kontakty a další plánované aktivity.

## Technologický Stack

- **Backend:** Spring Boot
- **Databáze:** PostgreSQL
- **Bezpečnost:** Spring Security
- **Kontejnerizace:** Docker

## Prerekvizity

Pro běh aplikace je potřeba mít nainstalované:

- Java JDK 17
- Docker
- Docker Compose

## Lokální Setup

1. **Klonování repozitáře**
    ```bash
    git clone [URL repozitáře]
    cd do adresáře projektu
    ```

2. **Spuštění databáze pomocí Docker Compose**
    ```bash
    docker-compose up -d
    ```

3. **Konfigurace aplikace**

    Ujistěte se, že soubor `application.yaml` obsahuje správné údaje pro připojení k databázi:
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/kalendar
        username: user
        password: heslo
    ```

4. **Sestavení a spuštění aplikace**
    ```bash
    ./gradlew bootRun
    ```

    Aplikace běží na `http://localhost:2024/api`.

## Bezpečnost

Aplikace využívá Spring Security pro autentizaci a autorizaci. Zabezpečení koncových bodů je nastaveno v souboru `WebSecurityConfig`.

## Testování

Pro spuštění testů:
```bash
./gradlew test
