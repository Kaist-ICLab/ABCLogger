const fs = require('fs');

try {
  // Read the contents of x.json
  const log_stdout = process.stdout;
  const rawData = fs.readFileSync("android/app/version.json");
  const jsonData = JSON.parse(rawData);

  // Extract versionCode and versionName
  const versionCode = jsonData.versionCode;
  const versionName = jsonData.versionName;

// //   // Output the extracted values
  log_stdout.write(`Version Code: ${versionCode}`);
  log_stdout.write(`Version Name: ${versionName}`);

  // Set the output variables for later use in the workflow
  console.log(`{versionCode}={${versionCode}}`);
  console.log(`{versionName}={${versionName}}`);
} catch (error) {
  console.error('Error reading version.json:', error.message);
  process.exit(1);
}