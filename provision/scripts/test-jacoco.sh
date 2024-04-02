#!/usr/bin/bash

mvn clean test jacoco:report &> /dev/null && \
firefox --new-tab http://localhost:63342/test/target/site/jacoco/index.html
