######### Variables definitions #########
image_name = product-service:latest
registry_url = localhost:5000

######### Main tasks definition #########
.PHONY: help

## Show commands help
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

build: clean check ## Build the project using gradle, build the image in docker, tag the image and upload it into a local registry
	gradle build
	docker build --tag=$(registry_url)/$(image_name) .
	docker push $(registry_url)/$(image_name)

######### Support tasks definition #########
clean:
	rm -rf .gradle gradle gradlew* build

# No matter if any of these tasks failed
	-docker rmi $(registry_url)/$(image_name)

check:
    ifeq (, $(shell which gradle))
		$(error "This script uses gradle and it isn't installed - please install it and try again!")
		exit 1
    endif

    ifeq (, $(shell which docker))
		$(error "This script uses docker and it isn't installed - please install it and try again!")
		exit 1
    endif