CREATE TABLE login_user (
  id bigserial NOT NULL,
  login_name varchar(32) NOT NULL,
  password varchar(64) NOT NULL,
  display_name varchar(32) NOT NULL,
  status varchar(16) NOT NULL,
  updated_at timestamp with time zone NOT NULL,
  created_at timestamp with time zone NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (login_name)
);

COMMENT ON TABLE login_user IS 'ログインユーザー';

COMMENT ON COLUMN login_user.id IS 'id';
COMMENT ON COLUMN login_user.login_name IS 'ログインID';
COMMENT ON COLUMN login_user.password IS 'パスワード(ハッシュ化)';
COMMENT ON COLUMN login_user.display_name IS '表示ユーザー名';
COMMENT ON COLUMN login_user.status IS '状態';
COMMENT ON COLUMN login_user.updated_at IS '更新日時';
COMMENT ON COLUMN login_user.created_at IS '登録日時';

CREATE INDEX ON login_user (status);

CREATE INDEX ON login_user (created_at);
