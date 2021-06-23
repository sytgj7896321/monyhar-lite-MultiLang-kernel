package main

import (
	"fmt"
	"os/exec"
	"runtime"
)

var commands = map[string]string{
	"windows": "cmd",
	"darwin":  "open",
	"linux":   "xdg-open",
}

// open calls the OS default program for url
func open(url string) error {
	run, ok := commands[runtime.GOOS]
	if !ok {
		return fmt.Errorf("don't know how to open things on %s platform", runtime.GOOS)
	}
	if run == "cmd" {
		return exec.Command(run, "/c", "start", url).Start()
	} else {
		return exec.Command(run, url).Start()
	}
}

func main() {
	_ = open("https://github.com/tucaoba2333/monyhar-lite-MultiLang-kernel")
}
