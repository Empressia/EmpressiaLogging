# Empressia Logging

## 目次

* [概要](#概要)
* [使い方](#使い方)
	* [ライブラリの設定](#ライブラリの設定)
	* [ロガーの使用](#ロガーの使用)
	* [設定ファイルの配置](#設定ファイルの配置)
* [ライセンス](#ライセンス)
* [使用しているライブラリ](#使用しているライブラリ)

## 概要

Empressia製のログライブラリです。  
Java Logging APIのWrapper程度ですが、  
設定と呼び出しを簡単にできるように補助します。  

## 使い方

### ライブラリの設定

ライブラリを追加します。  
Gradleであれば、例えば、以下のようにします。  

```groovy
dependencies {
	implementation(group:"jp.empressia", name:"jp.empressia.logging", version:"1.1.0");
}
```

バージョンは、適宜、調整してください。

### ロガーの使用

ELoggerのloggerメソッドを呼ぶことで、ロガーが取得できます。  

引数には、thisやクラス、パッケージを指定することで、  
そのパッケージ名に対応するLoggerを取得できます。  

取得されたLoggerは、Java Logging APIのものとなります。  

具体的には、例えば、以下のように、使用します。  
```java
import static jp.empressia.logger.Elogger.logger;
```

```java
logger(this).info("message");
```

### 設定ファイルの配置

Java Logging APIの設定ファイルを、  
クラスパス直下にlogging.propertiesとしておくと、  
ELogger呼び出し時に、設定を読み込みんで上書きします。  

以下のJava Logging API用のシステムプロパティを設定している場合は、読み込まれません。  
* java.util.logging.config.file
* java.util.logging.config.class

## ライセンス

いつも通りのライセンスです。  
zlibライセンス、MITライセンスでも利用できます。  

ただし、チーム（複数人）で使用する場合は、MITライセンスとしてください。  

## 使用しているライブラリ

特にありません。

## 注意

プロジェクトはVSCodeのJava拡張機能ではテストを実行できないようです（2021/05/01）。  
Gradleからは実行できます。  
