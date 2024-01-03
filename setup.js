const fs = require('fs');

try {
  // Read the contents of x.json
  const rawData = fs.readFileSync("android/app/version.json");
  const jsonData = JSON.parse(rawData);

  // Extract versionCode and versionName
  const versionCode = jsonData.versionCode;
  const versionName = jsonData.versionName;

  // Output the extracted values
  console.log(`Version Code: ${versionCode}`);
  console.log(`Version Name: ${versionName}`);

  // Set the output variables for later use in the workflow
  console.log(`{versionCode}=={${versionCode}} >> $GITHUB_OUTPUT`);
  console.log(`{versionName}=={${versionName}} >> $GITHUB_OUTPUT`);
} catch (error) {
  console.error('Error reading version.json:', error.message);
  process.exit(1);
}