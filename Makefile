# CONFIGURATION
include .env


SHELL = /bin/bash
ROOT_DIR = $(shell pwd)
SCRIPT_DIR = $(ROOT_DIR)/provision/scripts

# EXECUTABLE COMMANDS
CLEAN = $(SHELL) $(SCRIPT_DIR)/clean.sh
GIT_INIT = $(SHELL) $(SCRIPT_DIR)/git-init.sh
PRE-COMMIT-CONFIG = $(SHELL) $(SCRIPT_DIR)/pre-commit-config.sh
TEST_JACOCO = $(SHELL) $(SCRIPT_DIR)/test-jacoco.sh

configure:
	@$(CLEAN)
	@$(GIT_INIT)
	@$(PRE-COMMIT-CONFIG)

test:
	@$(TEST_JACOCO)
