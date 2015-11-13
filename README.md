# Plan-it Software Requirments
* Git
* Node.js with npm
* Bower<br />
    ```
    npm install -g bower
    ```
* Android Studio
* Visual Studio Code or any text editor like Sublime Text, Atom, Brackets etc.

#Setup and installion
* Node.js (backend code)<br />
To get all the npm packages listed in the packasge.json file, run this command
    ```
    npm install
    ```
* Angular
    ```
    bower install
    ```
* Android<br />
Open the project through Android Studio

#Contributing Code

For contributing code, please follow these steps:

1.  Fork Plan-It<br />
2.  Clone repo to dev environemnt

    ```
    git clone https://github.com/{Your GitHub Account}/Plan-it.git
    ```

3.  Add upstream remote

    ```
    git remote add upstream https://github.com/Team-Murphy/Plan-it.git
    ```
    
4.  Update to the latest revision

    ```
    git pull upstream
    ```
    
5.  Create a branch
    ```
    git checkout -b feature-name
    ```
    
6. Commit code to your dev repo
    ```
    git push --all origin
    ```
    
7. Submit a pull request

__Please ensure you pull the upstream repo before commiting to reduce the chances of having a merge conflict__<br />
__Please ensure that all pull requests are accompanied with tests__
