oc login --token=sha256~1EZRCdsrHxX_xL3yvZZcHKNnh5fxGWTyeg8ml0-lP9c --server=https://api.cluster-gbsmc.gbsmc.sandbox13.opentlc.com:6443
oc project user7-quarkus-eda
mvn clean package
oc apply -f target/kubernetes/openshift.yml
oc start-build quarkus-eda --from-dir=target/quarkus-app