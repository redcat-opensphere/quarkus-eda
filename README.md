# quarkus-eda Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## API

Use /api/topic endpoints with POST, GET or DELETE to write, read or clear messages
- Send a message ```curl -X POST http://quarkus-eda-user7-quarkus-eda.apps.cluster-gbsmc.gbsmc.sandbox13.opentlc.com/api/topic -H 'Content-Type: application/json' -d '{"key":"mensaje1","value":"Hello World"}'```
- Read all messages: ```curl -X GET http://quarkus-eda-user7-quarkus-eda.apps.cluster-gbsmc.gbsmc.sandbox13.opentlc.com/api/topic -H 'Accept: application/json'```
- Clear all messages: ```curl -X DELETE http://quarkus-eda-user7-quarkus-eda.apps.cluster-gbsmc.gbsmc.sandbox13.opentlc.com/api/topic -H 'Accept: application/json'```
    }
