import { spawn } from 'child_process';

const command = 'node';
const args = ['index.js'];
const input1 = '26\n';  // 수정된 입력값
const input2 = '타파스-1\n';  // 수정된 입력값
//const input2 = '해산물파스타-2,레드와인-1,초코케이크-1\n';
//const input2 = '양송이수프-2,타파스-1,시저샐러드-1,티본스테이크-1,바비큐립-2,해산물파스타-1,초코케이크-3,제로콜라-2,레드와인-1\n';
const childProcess = spawn(command, args, {
  stdio: 'pipe',
  shell: true,
});

childProcess.stdin.write(input1);
setTimeout(() => {
    childProcess.stdin.write(input2);
    /*
    setTimeout(() => {
      childProcess.stdin.write(input3);
      childProcess.stdin.end();
    }, 500);
    */
  }, 500);

childProcess.stdout.on('data', (data) => {
  console.log(data.toString());
});

childProcess.stderr.on('data', (data) => {
    console.error(data.toString()); // 출력된 에러 메시지를 콘솔에 표시
  });

childProcess.on('error', (err) => {
  console.error('오류 발생:', err);
});

childProcess.on('close', (code) => {
  console.log('프로세스 종료. 종료 코드:',code);
});