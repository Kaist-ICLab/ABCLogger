const fs = require('fs');

try {
  // Read the contents of x.json
  const github_output = process.env.GITHUB_OUTPUT;
  const rawData = fs.readFileSync("android/app/version.json");
  const jsonData = JSON.parse(rawData);

  // Extract versionCode and versionName
  const versionCode = jsonData.versionCode;
  const versionName = jsonData.versionName;

// //   // Output the extracted values
  console.log(`Version Code: ${versionCode}`);
  console.log(`Version Name: ${versionName}`);

  console.log(github_output);


  // Set the output variables for later use in the workflow
  fs.appendFileSync(github_output,`versionCode=${versionCode}`);
  fs.appendFileSync(github_output,`versionName=${versionName}`);
} catch (error) {
  console.error('Error reading version.json:', error.message);
  process.exit(1);
}