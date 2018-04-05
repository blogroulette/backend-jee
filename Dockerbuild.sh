#!/bin/bash

set -x

mvn install -q
docker build -t saschascherrer/blogroulette-backend .

