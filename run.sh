#!/usr/bin/env bash
rm -f *.log
java -jar -Dspring.profiles.active=prod ./target/anayticsTool-0.0.1-SNAPSHOT.jar