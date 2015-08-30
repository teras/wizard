package panos.awt;

public interface WizardListener
{
	public void clickedFinish ();
	public void clickedCancel ();
	public void changedPage( int pg );
	public void performedAction ( wizButton wb );
}
