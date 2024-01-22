const fs = require('fs')
var admin = require("firebase-admin");

admin.initializeApp();

editNPublishTemplate()
function editNPublishTemplate() {
  const versionData = JSON.parse(fs.readFileSync("android/app/version.json"));
  console.log(versionData["versionName"]);
  var config = admin.remoteConfig();
  config.getTemplate()
    .then(function (template) {
      template["parameters"]["versionCode"]["defaultValue"]["value"] = String(versionData["versionCode"])
      template["parameters"]["versionName"]["defaultValue"]["value"] = versionData["versionName"]
      console.log(template);
      config.publishTemplate(config.createTemplateFromJSON(
        JSON.stringify(template)
      )).then(function (updatedTemplate) {
        console.log('Template has been published');
        console.log('ETag from server: ' + updatedTemplate.etag);
      }).catch(function (err) {
        console.error('Unable to publish template.');
        console.error(err);
      });
    })
    .catch(function (err) {
      console.error('Unable to get template');
      console.error(err);
    });
}
