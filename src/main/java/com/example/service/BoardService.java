package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardEntity;
import com.example.domain.BoardInfoEntity;
import com.example.mapper.BoardInfoMapper;
import com.example.mapper.BoardMapper;
import com.example.mapper.BoardReplyMapper;

@Service
public class BoardService {

	@Autowired
	private BoardInfoMapper boardInfoMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardReplyMapper boardReplyMapper;
	
	private List<BoardInfoEntity> infoEntity;
	
	//추후 redis를 이용한 local cache에서 값을 가져오는 코드로 수정 필요
	@PostConstruct
	private void setTempLocalCache() {
		this.infoEntity = boardInfoMapper.getBoardInfoList();
	}
	
	@Transactional(readOnly = true)
	public List<BoardEntity> getBoardListByInfoId(Integer infoId) throws Exception {
		boardInfoReadExceptionCheck(infoId);
		return boardMapper.getBoardListByInfoId(infoId);
	}
	
	@Transactional(readOnly = true)
	public BoardEntity getBoardById(Long id) throws Exception {
		BoardEntity entity = boardMapper.getBoardById(id);
		checkBoardException(entity);
		boardInfoReadExceptionCheck(entity.getInfoId());
		entity.setReplyList(boardReplyMapper.getBoardReplyListByBoardId(id));
		return entity;
	}
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createBoard(BoardEntity entity) throws Exception {
		boardInfoWriteExceptionCheck(entity.getInfoId());
		boardMapper.createBoard(entity);
	}
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBoard(BoardEntity entity) throws Exception {
		boardInfoWriteExceptionCheck(entity.getInfoId());
		boardMapper.updateBoard(entity);
	}
	
	
	public void boardInfoReadExceptionCheck(Integer infoId) throws Exception {
		checkBoardInfoException(infoId, false, false);
	}
	
	public void boardInfoWriteExceptionCheck(Integer infoId) throws Exception {
		checkBoardInfoException(infoId, true, true);
	}
	
	//로컬 캐시에 저장된 info entity의 정보를 이용해 오류체크하는 메소드, 타 서비스에서의 호출을 허용하기 위한 public 선언
	public void checkBoardInfoException(Integer boardInfoId, Boolean isAdminCheck, Boolean isWrite) throws Exception {
		infoEntity.stream().forEach(e-> System.out.print(e));
		Optional<BoardInfoEntity> info = infoEntity.stream().filter(e -> e.getId().equals(boardInfoId)).findFirst();
		if(info.isEmpty()) {
			throw new Exception("찾을 수 없는 게시판");
		} else {
			BoardInfoEntity entity = info.get();
			if(!entity.getIsUse()) {
				throw new Exception("사용 중지된 게시판");
			} else if(isWrite && entity.getIsReadOnly()) {
				throw new Exception("읽기 전용 게시판");
			} else if(isWrite && isAdminCheck && entity.getIsAdminOnly()) {
				throw new Exception("관리자 전용 게시판");
			}
		}
	}
	
	public void checkBoardExceptionById(Long boardId) throws Exception {
		checkBoardException(boardMapper.getBoardById(boardId));
	}
	
	// 게시물의 entity 정보를 이용해 오류 체크하는 메소
	public void checkBoardException(BoardEntity entity) throws Exception {
		//위와 마찬가지로 null check, is_del 체크 등을 처리
		if(entity == null) {
			throw new Exception("찾을 수 없는 게시물");
		}
		// 차후 삭제 여부, 당사자 작성 여부 등 오류체크
	}
}
