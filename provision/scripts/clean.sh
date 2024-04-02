#!/usr/bin/bash

GIT_FILE="dot.gitignore"
GIT_NEW_NAME=".gitignore"

if [ -f "$GIT_FILE" ]; then
    mv "$GIT_FILE" "$GIT_NEW_NAME"
fi
