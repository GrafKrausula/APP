package app.exercise.visualtree;
/**
 * Das Java-Interface DrawableTreeElement.
 *
 * @author Marcel Neitzel
 */


 /**
  * Dieses Interface ist für die Knoten-Klasse des binären Suchbaums.
  */
public interface DrawableTreeElement<T> {
	public DrawableTreeElement<T> getLeft();
	public DrawableTreeElement<T> getRight();
	public boolean isRed();
	public T getValue();
}
