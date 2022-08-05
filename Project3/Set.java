
public interface Set {
/**
* Adds the element to the set, or ignores the element
* if it is already contained in the set.
*
* @param element The element to add
*/
void add(String element);
/**
* Queries whether an element is contained in the set.
*
* @param element The element to query for containment
* @return Whether the element is contained in the set
*/
boolean contains(String element);
}
