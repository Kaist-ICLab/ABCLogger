const fs = require('fs')
var admin = require("firebase-admin");

var serviceAccount = require("../firebase-admin-sdk.json");
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

checkVersion()
function checkVersion() {
  const versionData = JSON.parse(fs.readFileSync("android/app/version.json"));
  var config = admin.remoteConfig();
  config.getTemplate()
    .then(function (template) {
      if(Number(template["parameters"]["versionCode"]["defaultValue"]["value"]) >= versionData["versionCode"]){
        console.error("Version Code is lower!");
      }
    })
    .catch(function (err) {
      console.error('Unable to get template');
      console.error(err);
    });
}
