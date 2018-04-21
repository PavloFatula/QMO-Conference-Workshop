QMO-Conference-Workshop
===
This repository contain dummy test automation project with some tests. Workshop notes are [here](https://gist.github.com/extsoft/d8bd1b3c138bfe7b8136237e58857454). 

Run app
===
`docker-compose -f selenium-hub.yml -f opencart.yml up -d` will up application together with the Selenium Grid (Chrome and Firefox nodes).

Run tests
===
Maven
-----
```bash
$ mvn clean test
```

Statistic
---
Tests run: 10, Failures: 8, Errors: 0, Skipped: 0
