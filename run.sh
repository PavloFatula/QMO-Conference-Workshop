#!/usr/bin/env bash

echo "Wait 1 minute for system will wake up"
sleep 20

mvn --offline test $@
