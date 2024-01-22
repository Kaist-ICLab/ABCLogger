const fs = require('fs');

try {
  // Read the contents of x.json
  const rawData = fs.readFileSync("android/app/version.json");
  const jsonData = JSON.parse(rawData);

  // Extract versionCode and versionName
  const versionCode = jsonData.versionCode;
  const versionName = jsonData.versionName;

  // Set the output variables for later use in the workflow
  console.log(`versionCode=${versionCode}`);
  console.log(`versionName=${versionName}`);
} catch (error) {
  console.error('Error reading version.json:', error.message);
  process.exit(1);
}