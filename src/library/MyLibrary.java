package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.entity.EntityFactory;
import library.entity.IBasicEntity;
import library.entity.IBorrowableEntity;
import library.exception.CantRestoreEntity;
import library.exception.DuplicateIdentifierException;
import library.exception.NotFoundEntityException;
import library.exception.NotFoundMemberException;
import library.exception.TypeDoesntExisteException;
import library.exception.UnavailableEntityException;
import library.exception.UnborrowableEntityException;
import library.user.Member;

public class MyLibrary implements ILibrary {
	private Map<String,IBasicEntity> entities = new HashMap<>();
	private Map<String,Member> members = new HashMap<>();
	private Map<String,List<String>> borrowing = new HashMap<>();
	private EntityFactory entityFactory = new EntityFactory();
	
	@Override
	public void registerEntity(String id, String title, String autor, String entityType)
			throws DuplicateIdentifierException, TypeDoesntExisteException {
		
		IBasicEntity entity = entityFactory.createEntity(id,title,autor,entityType);
		if(entity == null) {
			throw new TypeDoesntExisteException("the type "+ entityType + "doesn't exist");
		}
		
		if(entities.putIfAbsent(id,entity) != null) {
			throw new DuplicateIdentifierException("id "+id +" is already used for entity");
		}
	}

	@Override
	public void registerMember(String id, String name, String firstname, String mail) throws DuplicateIdentifierException {
		Member m = new Member(id,name,firstname,mail);
		
		if(members.putIfAbsent(id,m) != null) {
			throw new DuplicateIdentifierException("id "+id +" is already used for member");
		}
	}

	@Override
	public void borrowEntity(String entityid, String memberid)
			throws UnborrowableEntityException, NotFoundEntityException, NotFoundMemberException, UnavailableEntityException {
		
		IBasicEntity entity = entities.get(entityid);
		if(entity == null) {
			throw new NotFoundEntityException(entityid + " not found");
		}
		if(! (entity instanceof IBorrowableEntity)) {
			throw new UnborrowableEntityException(entityid + " is not a borrowable");
		}
		
		IBorrowableEntity borrowableEntity = (IBorrowableEntity)entity;
		if(! borrowableEntity.isAvailable()) {
			throw new UnavailableEntityException(entityid + " is not available");
		}
		
		Member m = members.get(memberid);
		if( m == null) {
			throw new NotFoundMemberException(memberid + " not found");
		}
		
		
		if( ! borrowing.containsKey(memberid)) {
			borrowing.put(memberid,new ArrayList<>());
		}
		borrowing.get(memberid).add(entityid);
		borrowableEntity.borrow();
	}

	@Override
	public void restoreEntity(String entityid, String memberid)
			throws UnborrowableEntityException, NotFoundEntityException, CantRestoreEntity {
		
		if( ! this.borrowed(entityid,memberid)) {
			throw new CantRestoreEntity("member " + memberid +" did not borrow "+ entityid);
		}
		
		IBasicEntity entity = entities.get(entityid);
		if(entity == null) {
			throw new NotFoundEntityException(entityid + " not found");
		}
		if(! (entity instanceof IBorrowableEntity)) {
			throw new UnborrowableEntityException(entityid + " is not a borrowable");
		}
		
		IBorrowableEntity borrowableEntity = (IBorrowableEntity)entity;
		if(! borrowableEntity.restore()) {
			throw new CantRestoreEntity("can't restore "+ entityid);
		}
		
		borrowing.get(memberid).remove(entityid);
		if( borrowing.get(memberid).isEmpty()) {
			borrowing.remove(memberid);
		}
	}
	
	@Override
	public boolean borrowed(String entityid, String memberid) {
		return (borrowing.containsKey(memberid) && borrowing.get(memberid).contains(entityid));
	}
	
	@Override
	public boolean isAvailable(String entityid)
		throws NotFoundEntityException, UnborrowableEntityException {
		
		IBasicEntity entity = entities.get(entityid);
		if(entity == null) {
			throw new NotFoundEntityException(entityid + " not found");
		}
		if(! (entity instanceof IBorrowableEntity)) {
			throw new UnborrowableEntityException(entityid + " is not a borrowable");
		}
		
		IBorrowableEntity borrowableEntity = (IBorrowableEntity)entity;
		return borrowableEntity.isAvailable();
	}

}
