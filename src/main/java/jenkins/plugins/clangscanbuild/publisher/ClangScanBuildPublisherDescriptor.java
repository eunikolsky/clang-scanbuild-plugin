package jenkins.plugins.clangscanbuild.publisher;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;


public class ClangScanBuildPublisherDescriptor extends BuildStepDescriptor<Publisher>{

	public ClangScanBuildPublisherDescriptor(){
		super( ClangScanBuildPublisher.class );
		load();
	}
	
	@Override
	public Publisher newInstance(StaplerRequest arg0, JSONObject json ) throws hudson.model.Descriptor.FormException {

		boolean markBuildUnstable = false;
		int bugThreshold = 0;
		
		JSONObject failWhenThresholdExceeded = json.optJSONObject( "failWhenThresholdExceeded" );
		if( failWhenThresholdExceeded != null ){
			markBuildUnstable = true;
			bugThreshold = failWhenThresholdExceeded.getInt( "bugThreshold" );
		}
		
		return new ClangScanBuildPublisher(  markBuildUnstable, bugThreshold );
	}
	
	@Override
	public String getDisplayName() {
		return "Publish Clang Scan-Build Results";
	}

	@Override
	public boolean isApplicable( @SuppressWarnings("rawtypes") Class<? extends AbstractProject> jobType ){
		return true;
	}

}
