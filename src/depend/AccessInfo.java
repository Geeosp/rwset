package depend;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;

/**
 * This class encapsulates the information that should be
 * in the nodes of the dependency graph. See docs/project1.txt
 *  
 */
class AccessInfo {

  // identifies where in the code the access has occurred
  public final IClass accessClass;
  public final IMethod accessMethod;
  public final int accessLineNumber;
  
  // identifies what information has been accessed
//  public final TypeReference fieldReferenceDeclaringClass;
  public final IField iField;

  AccessInfo(IClass accessClass, 
      IMethod accessMethod, 
      int accessLineNumber, 
      IField ifield) {
    this.accessClass = accessClass;
    this.accessMethod = accessMethod;
    this.accessLineNumber = accessLineNumber;
    this.iField = ifield;
  }
  
  // auto-generated by Eclipse
  @Override
  public String toString() {
    return "AccessInfo [accessClass=" + accessClass + ", accessMethod="
        + accessMethod + ", accessLineNumber=" + accessLineNumber
        + ", fieldDefinition=" + iField + "]";
  }

  // auto-generated by Eclipse
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accessClass == null) ? 0 : accessClass.hashCode());
    result = prime * result + accessLineNumber;
    result = prime * result + ((accessMethod == null) ? 0 : accessMethod.hashCode());
    result = prime * result + ((iField == null) ? 0 : iField.hashCode());
    return result;
  }

  // auto-generated by Eclipse
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AccessInfo other = (AccessInfo) obj;
    if (accessClass == null) {
      if (other.accessClass != null)
        return false;
    } else if (!accessClass.equals(other.accessClass))
      return false;
    if (accessLineNumber != other.accessLineNumber)
      return false;
    if (accessMethod == null) {
      if (other.accessMethod != null)
        return false;
    } else if (!accessMethod.equals(other.accessMethod))
      return false;
    if (iField == null) {
      if (other.iField != null)
        return false;
    } else if (!iField.equals(other.iField))
      return false;    
    return true;
  }
}