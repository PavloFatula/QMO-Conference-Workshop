QMO-Conference-Workshop
===
This repository contain dummy test automation project with some tests. Workshop notes are [here](https://gist.github.com/extsoft/d8bd1b3c138bfe7b8136237e58857454). 

Local run
---
For local execution we have to run SUT (system under test). After, we need to run tests configuring desired browser. The commands below demonstrates process:

```bash
# run SUT
docker-compose -p sut -f opencart.yml up -d
# run tests
mvn clean test -Dsut-domain="localhost" -Dbrowser=chrome
# destroy SUT
docker-compose -p sut -f opencart.yml up -d
```

Available `browser`s:
- `chrome`
- `firefox`

Status: `Tests run: 12, Failures: 1, Errors: 0, Skipped: 0`

Docker run
---
For Docker execution we have to build an image with tests, configure tests together with Selenium Hub (see [tests.yaml](tests.yml) for the details) and run Tests and Hub together with the SUT. The commands below demonstrates process:
```bash
# run SUT and tests (press Ctrl+C to break the command)
docker-compose -p all -f tests.yml -f opencart.yml up --build 
# destroy
docker-compose -p all -f tests.yml -f opencart.yml down
``` 

Please take into account that defaults are set in the [Dockerfile](Dockerfile). Add `command: "your defaults"` to `opencart-test` service in  [tests.yaml](tests.yml) if you would like to change it.

Available `browser`s:
- `remote-chrome`
- `remote-firefox`

Test reports are available under `./reports`. 

Status: `Tests run: 11, Failures: 9, Errors: 0, Skipped: 0`

