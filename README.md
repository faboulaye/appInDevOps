# appInDevOps
Simple application build in devops manner

Deploy infrastructure as code
```
aws cloudformation create-stack --template-body file://devops-template.yml --stack-name app-devops --parameters file://parameters.json
```
