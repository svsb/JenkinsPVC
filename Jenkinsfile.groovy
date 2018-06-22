node('win'){
   try{
		
       	def gitRepo 	= "github.aig.net/commercial-it-config/devops-esurety-devcicd-1413.git"; 			
        def branchName  = "master";
	def node_os = isUnix() ? 'LINUX' : 'WIN';
		//clone the config repo
		clonerepo(node_os, gitRepo, branchName);	   
		//def stageArray = prepareStages();
		executeBuild(node_os);
   } catch (Exception e){
	throw e;
   } finally{
      //deleteDir();
	  //step([$class: 'AuditPipelinePublisher', enabled: true]);
   }
}

def clonerepo(node_os, repo_url, branch){
 {
		if(node_os == 'WIN') {
			
			//bat "git clone -b master https://github.com/svsb/JenkinsPVC.git";
			println "Cloned the repo";
		}
		else
		{
			//sh "scl enable rh-git29 -- git clone -b ${branch} https://${comm_git_clone_token}@${repo_url}"
			//println "Cloned the repo";
		}
    }
}
def executeBuild(node_os){

	try{        
		
		stage ("Checkout") {
		
		println "Checkout";
		
			try{
				if(node_os == 'WIN'){
				
					bat "git clone -b master https://github.com/svsb/JenkinsPVC.git";					
					
				}else{
					//sh 'java -version';
					//sh "git config --global core.longpaths true";
				}
				
				//git poll: false, credentialsId: gitCredId, url: gitRepo, branch: branchName;
				
				println "Success";
				
			}catch(Exception e){
				 //stageArray.put('Checkout', 'Failure');
				 throw e;
			}
		}
		
		stage("Build Automation"){
		
			println "Build Automation";
			
			try{
				if(node_os == 'WIN'){
				def jdkHome = 'jdk1.6';				
					withEnv(["JAVA_HOME=${jdkHome}"]){
						bat '''
							cd \\build
							java -version
							suretyBuild_custom.bat
						'''
					}
				}else{
					///jdkHome = tool 'jdk1.6';
					//withEnv( ["JAVA_HOME=${jdkHome}"] ){
					//	
					//	sh '''
					//		java -version;
					//		ls;
					//		cd build;
					//		chmod 777 suretyBuild_custom.sh;
					//		./suretyBuild_custom.sh;
					//	'''
					//	sh "mv build/target/${earName} build/target/${artifactoryBuildName}-${buildVersion}.ear";*/
					}
				}
					
				println "Build Automation";
				
			}catch(Exception e){
				//stageArray.put('Build Automation', 'Failure');
				throw e;
			}
		}
		
		stage ("Unit Testing"){

			
			try{
				//not applicable				
				//stageArray.put('Unit Testing', 'Success');
			}
			catch(Exception e){
				 //stageArray.put('Unit Testing', 'Failure');
				 throw e;
			}
		}
		
		stage ("Code Coverage"){
			//env.CURRENT_STAGE = 'Code Coverage';
			
			try{
				//not applicable				
				//stageArray.put('Code Coverage', 'Success');
			}
			catch(Exception e){
				 //stageArray.put('Code Coverage', 'Failure');
				 throw e;
			}
		}
		
		stage ("Security Scan Status"){
			//env.CURRENT_STAGE = 'Security Scan Status'
			try{
				//placeholder
				//stageArray.put('Security Scan Status', 'Success');
			}catch(Exception e){
				//stageArray.put('Security Scan Status', 'Failure');
				//throw e;
			}
		}

    }catch(Exception e){
		//currentBuild.result = 'FAILURE';
		//sendEmailNotification('FAILURE',env.CURRENT_STAGE,'Failure',env.photonURL,env.artifactoryURL,env.emailTo,env.sonarURL,stageArray);
	}
}

//import java.text.SimpleDateFormat;
//def prepareStages(){
//	def dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//	def start = new Date();
//	env.build_start = dateFormat.format(start);
//	def stageArray = [:]
//	stageArray.put('Checkout', 'Not started');
//	stageArray.put('Build Automation', 'Not started');
//	stageArray.put('Unit Testing', 'Not started');
//	stageArray.put('Code Coverage','Not started');
//	stageArray.put('Security Scan Status', 'Not started');
//	stageArray.put('Build Management', 'Not started');
//	stageArray.put('EAR Deployment', 'Not started');
//	return stageArray;
//}

return this