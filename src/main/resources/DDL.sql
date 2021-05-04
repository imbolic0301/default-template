#dd

CREATE TABLE `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `info_id` int(11) NOT NULL COMMENT '게시판 종류',
  `user_id` bigint(20) NOT NULL,
  `category` tinyint(4) NOT NULL DEFAULT '1' COMMENT '게시판 내 세부 카테고리',
  `title` varchar(128) DEFAULT '',
  `content` text,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mod_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;



CREATE TABLE `board_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '게시판 이름',
  `is_admin_only` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 : false, 1 : true (admin only 여부)',
  `is_read_only` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 : false, 1 : true (read only 여부)',
  `is_use` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 : false, 1 : true (사용 여부)',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mod_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `board_inquiry` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 : 질문/답변 게시판, 2: 문의 게시판, 3: 기능 요청 게시판 (게시판 종류)',
  `title` varchar(128) DEFAULT NULL,
  `content` text,
  `answer` text,
  `parent_id` bigint(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mod_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `board_reply` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `board_id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '대댓글의 대상인 댓글 id',
  `origin_id` bigint(20) DEFAULT NULL COMMENT '대댓글이 달리기 시작했던 댓글의 id',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 : false, 1 : true (삭제 여부)',
  `reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mod_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
