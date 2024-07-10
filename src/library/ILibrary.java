package library;

import library.exception.CantRestoreEntity;
import library.exception.DuplicateIdentifierException;
import library.exception.NotFoundEntityException;
import library.exception.NotFoundMemberException;
import library.exception.TypeDoesntExisteException;
import library.exception.UnavailableEntityException;
import library.exception.UnborrowableEntityException;

public interface ILibrary {
	/* Ce chargera de gerer la bibliotheque et aura pour role de controller la validité de toutes les actions
	relative aux entitées et de l'unicité de ces mêmes entitées.
	*/
	public void registerEntity(String id, String title, String autor, String entityType) throws DuplicateIdentifierException, TypeDoesntExisteException;
	public void registerMember(String id, String name, String prenom, String mail) throws DuplicateIdentifierException;
	public void borrowEntity(String entityid, String idMember) throws UnborrowableEntityException, NotFoundEntityException, NotFoundMemberException, UnavailableEntityException;
	public void restoreEntity(String entityid, String idMember) throws UnborrowableEntityException, NotFoundEntityException, CantRestoreEntity;
	boolean borrowed(String entityid, String memberid);
	boolean isAvailable(String entityid) throws NotFoundEntityException, UnborrowableEntityException;
}