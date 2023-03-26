######### Main tasks definition #########
.PHONY: help

## Show commands help
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

undeploy_all: ## Undeploy all projects from k8s cluster
	kubectl delete -f k8/ingress.yaml -n spring-cloud-demo
	$(MAKE) -C product-service undeploy
	$(MAKE) -C customer-service undeploy
	$(MAKE) -C shopping-service undeploy

deploy_all: ## Deploy all projects to k8s cluster
	$(MAKE) -C product-service deploy
	$(MAKE) -C customer-service deploy
	$(MAKE) -C shopping-service deploy
	kubectl apply -f k8/ingress.yaml -n spring-cloud-demo

build_all: ## Build all projects and load the images
	$(MAKE) -C product-service build
	$(MAKE) -C customer-service build
	$(MAKE) -C shopping-service build
	kubectl apply -f k8/ingress.yaml -n spring-cloud-demo

load_images: ## Load the image of all projects in the Docker registry
	$(MAKE) -C product-service load_image
	$(MAKE) -C customer-service load_image
	$(MAKE) -C shopping-service load_image