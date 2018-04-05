#!/bin/bash

set -x

mvn install -q
docker build -t blogroulette/blogroulette-backend .


