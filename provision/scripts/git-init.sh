#!/usr/bin/bash

git init &> /dev/null && \
git config user.email "$EMAIL" && \
git config user.name "$NAME"