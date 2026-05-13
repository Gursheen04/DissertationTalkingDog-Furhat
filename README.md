# Overview
This project implements a zoomorphic robot‑dog interaction model using the Furhat Robotics platform. 
The system explores full‑duplex and half‑duplex non‑lexical backchanneling behaviours through custom 
Kotlin‑based Furhat skills. The repository contains all source code, assets, and build configurations required to 
run the skill in the Furhat SDK simulator or deploy it to a physical Furhat robot. 

## Prerequisites
Java
Gradle (wrapper included)
Furhat SDK installed
Furhat Robot (optional, for physical deployment)

## Running the Skill in the Furhat SDK
1. Build the project using: 
   ./gradlew build
2. Run the skill locally. This launches the skill in the Furhat SDK simulator: ./gradlew run
3. Ideally the virtual furhat should be running before implementing step 2. The simulator will start your skill if all works.

## Building a Deployable .skill File
1. The physical furhat runs your code as a .skill file. 
2. To package the skill into a single deployable file, use: ./gradlew shadowJar
3. This command compiles the project, bundles all dependencies, packages gestures, assets, and skill.properties, produces a .skill file
4. The output appears in the folder as build/libs/ as .skill

## Using the Pre‑Built Skill Files (FULL and HALF)
This repository includes pre‑built skill files for both interaction modes:

skill_builds/DissertationTalkingDog-FullDuplex.skill
skill_builds/DissertationTalkingDog-FullDuplex.skill
These are not used by the build system.
They are stored only for reference, versioning, and deployment.

## Deploying to a Physical Furhat Robot
### Option A — Build fresh from source
Run: ./gradlew shadowJar
Locate the generated .skill file in:
build/libs/
Upload it to the robot via:
Furhat Robot Dashboard → Skills → Upload Skill (there is a manual available in the HRI lab)

### Option B — Use the pre‑built skill files
If you want to deploy one of the pre‑built FULL or HALF versions:
Choose the desired .skill file from: skill_builds/
Copy one file at a time into: build/libs/
(This keeps the deployment folder clean and avoids confusion.)
Upload it to the robot via the web dashboard.

### Notes
Only one .skill file should be placed in build/libs/ at a time when deploying to the robot.
The simulator can run directly from source using gradlew run; no .skill file is needed (having one would be better)
The robot requires a packaged .skill file — it cannot run the project folder directly.

