package library;

import library.exception.CantRestoreEntity;
import library.exception.DuplicateIdentifierException;
import library.exception.NotFoundEntityException;
import library.exception.NotFoundMemberException;
import library.exception.TypeDoesntExisteException;
import library.exception.UnavailableEntityException;
import library.exception.UnborrowableEntityException;

public interface ILibrary {
	/* Ce chargera de gerer la bibliotheque et aura pour role de controller la validit� de toutes les actions
	relative aux entit�es et de l'unicit� de ces m�mes entit�es.
	*/
	public void registerEntity(String id, String title, String autor, String entityType) throws DuplicateIdentifierException, TypeDoesntExisteException;
	public void registerMember(String id, String name, String prenom, String mail) throws DuplicateIdentifierException;
	public void borrowEntity(String entityid, String idMember) throws UnborrowableEntityException, NotFoundEntityException, NotFoundMemberException, UnavailableEntityException;
	public void restoreEntity(String entityid, String idMember) throws UnborrowableEntityException, NotFoundEntityException, CantRestoreEntity;
	boolean borrowed(String entityid, String memberid);
	boolean isAvailable(String entityid) throws NotFoundEntityException, UnborrowableEntityException;
}