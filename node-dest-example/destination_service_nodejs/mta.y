ID: destination_service_nodejs
_schema-version: '2.1'
version: 0.0.1
modules:
  - name: destination_service_nodejs
    type: nodejs
    path: .
    requires:
      - name: destination

resources:
  - name: destination
    type: org.cloudfoundry.managed-service
    parameters:
      service-plan: lite
      service: destination
      shared: true
